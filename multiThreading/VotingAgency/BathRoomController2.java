package VotingAgency;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BathRoomController2 {
    private BathRoom bathRoom;
    private List<Thread> workers;
    private Thread schedulerThread;
    private LinkedList<Runnable> taskQueue;
    private List<Thread> taskThreads;
    private LinkedList<User> democrats, republicans;
    private volatile boolean running = true;
    private final Object taskLock = new Object();

    public BathRoomController2(BathRoom bathRoom, int workerCount) {
        this.bathRoom = bathRoom;
        this.democrats = new LinkedList<>();
        this.republicans = new LinkedList<>();
        this.taskQueue = new LinkedList<>();
        this.workers = new ArrayList<>();
        this.taskThreads = new ArrayList<>();

        for (int i = 0; i < workerCount; i++) {
            Thread worker = new Thread(() -> {
                while (running) {
                    Runnable task = null;
                    synchronized (taskLock) {
                        while (taskQueue.isEmpty() && running) {
                            try {
                                taskLock.wait();
                            } catch (InterruptedException e) {
                                return;
                            }
                        }
                        if (!taskQueue.isEmpty()) {
                            task = taskQueue.removeFirst();
                        }
                    }

                    if (task != null) {
                        task.run();
                    }
                }
            });
            worker.start();
            workers.add(worker);
        }
    }

    public void addUser(User user) {
        synchronized (democrats) {
            if (user.getUserType() == UserType.DEMOCRAT) {
                democrats.add(user);
            } else {
                republicanAdd(user);
            }
        }
    }

    private void republicanAdd(User user) {
        synchronized (republicans) {
            republicans.add(user);
        }
    }

    public void shutdown() {
        running = false;
        schedulerThread.interrupt();
        for (Thread t : workers) t.interrupt();

        synchronized (taskLock) {
            taskLock.notifyAll();
        }

        try {
            schedulerThread.join();
            for (Thread t : workers) t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        schedulerThread = new Thread(() -> {
            while (running) {
                User user = null;
                UserType currentType = bathRoom.getBathroomStatus();

                synchronized (democrats) {
                    synchronized (republicans) {
                        if (democrats.isEmpty() && republicans.isEmpty()) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                break;
                            }
                            continue;
                        }

                        if (currentType == null) {
                            user = !democrats.isEmpty() ? democrats.removeFirst() : republicans.removeFirst();
                        } else if (currentType == UserType.DEMOCRAT && !democrats.isEmpty()) {
                            user = democrats.removeFirst();
                        } else if (currentType == UserType.REPUBLICAN && !republicans.isEmpty()) {
                            user = republicans.removeFirst();
                        }
                    }
                }

                if (user != null && bathRoom.tryEnter(user)) {
                    User finalUser = user;
                    Runnable task = () -> {
                        try {
                            System.out.println("user " + finalUser.getId() + " has occupied with usertype as " + finalUser.getUserType());
                            Thread.sleep(finalUser.getBathRoomTimeInMillis());
                            System.out.println("user " + finalUser.getId() + " is now exiting ");
                            bathRoom.exit();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    };

                    synchronized (taskLock) {
                        taskQueue.addLast(task);
                        taskLock.notifyAll();
                    }
                } else if (user != null) {
                    System.out.println("user " + user.getId() + " failed to occupy with usertype as " + user.getUserType());
                    addUser(user);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        schedulerThread.start();
    }
}
