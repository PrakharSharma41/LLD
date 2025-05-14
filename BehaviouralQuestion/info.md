# Index

- [Invent and Simplify](#Invent-and-Simplify)
- [Ownership](#Ownership)
- [Bias for Action](#Bias-for-Action)
- [Dive Deep](Dive-Deep)
- [Learn and Be Curious](#Learn-and-Be-Curious)
- [Hire and Develop the Best / Teamwork](#Hire-and-Develop-the-Best-/-Teamwork)
- [Customer Obsession](#Customer-Obsession)
- [Insist on the Highest Standards](#Insist-on-the-Highest-Standards)
- [Have Backbone; Disagree and Commit](#Have-Backbone;-Disagree-and-Commit)
- [Deliver Results](#Deliver-Results)

# Invent and Simplify
Q: Give an example of a time you proposed a new idea or process. What was the response?
Answer:

Situation: As a part of the central team, we often received tickets that didn’t belong to us because reporters randomly assigned issues without proper triage.

Task: This created noise and unnecessary workload. I wanted to reduce these false positives and ensure correct ownership from the start.

Action: I proactively discussed this problem with my manager and designed a standardized procedure with clear documentation. It guided reporters to identify the appropriate team before assigning tickets.

Result: This significantly reduced false positives and improved triaging accuracy, saving time and increasing team productivity.

# Ownership
Q: Tell me about a time you took ownership of a problem others ignored.
Answer:

Situation: In Panacea, our log analysis tool processed all levels of logs (info, warning, fatal), leading to long report generation times and false positives.

Task: I aimed to optimize the log parsing process to prioritize critical information and reduce noise.

Action: I proposed a prioritized approach where we parse warning and fatal logs first and defer info logs unless relevant. I collaborated with the team to implement this logic.

Result: We achieved a 50% reduction in report generation time and significantly improved the quality of insights by focusing on what mattered most.

# Bias for Action
Q: Tell me about a time you made a decision with incomplete data.

Answer:

Situation: During a company hackathon, we aimed to automate the ticket update flow, but we didn’t have complete data around all Jira ticket transition conditions.

Task: Despite the uncertainty, we wanted to deliver an MVP that could handle the most common flows.

Action: I analyzed ticket patterns from existing logs, inferred the key transitions, and led the team in building a working end-to-end solution.

Result: The prototype worked well in most cases and was awarded Category Winner, validating our hypothesis and speeding up ticket updates by 30%.

# Dive Deep
Q: Describe a problem you solved by digging into the data.

Answer:

Situation: Our internal tool was generating many false positives when matching known issues from logs.

Task: We needed to improve its accuracy to avoid chasing already resolved or irrelevant problems.

Action: I analyzed false positive cases, identified weak patterns in our existing regex matching, and created custom regex rules for common issues in Prism components.

Result: This improved detection precision and reduced false positives by 40%, making the triage process much more reliable.

# Learn and Be Curious
Q: Tell me about something new you learned to solve a problem.

Answer:

Situation: I initially joined the team as a node js backend engineer, but key services for our ticket triaging and dials were backend-heavy, involving Java, Python, and Kubernetes.

Task: To be effective, I had to quickly ramp up on backend technologies and Kubernetes.

Action: I self-learned backend flows, started debugging services in Java/Python, and completed the CKAD certification to understand Kubernetes better.

Result: This enabled me to debug cross-service issues confidently and drive improvements in our triaging pipeline, especially during high-pressure release windows.

# Hire and Develop the Best / Teamwork
Q: How do you support teammates who are struggling?

Answer:

If someone on my team is lagging behind, I first try to understand why — skill gaps, lack of clarity, or personal challenges.

If it’s time-critical, I let them shadow a senior teammate to learn quickly and contribute right away. If we have time, I assign them a self-contained module to own, providing guidance and regular syncs.

This approach helps them feel supported, builds confidence, and ensures we deliver as a team.

# Customer Obsession
Q: Tell me about a time you went above and beyond for a customer.

Answer:
1. 
Situation: A production service suffered from heap out-of-memory errors, blocking customer access.

Task: I had to unblock the customer quickly while ensuring service stability.

Action: I overrode initial resource allocations, adjusted JVM heap and timeouts, and used backend command histories (like nuclei.history and SSH histories) to debug what had led to the memory spike.

Result: The service was restored quickly, and the customer impact was minimized. These changes were later made default for similar services to prevent recurrence.

The JVM heap size was previously tuned for average workloads, and the service used some in-memory caching that spiked due to unexpected data volume.
I analyzed GC logs and JVM stats, checked command histories (nuclei.history, bash_history) to identify heavy operations, and tuned the heap, GC, and timeout settings accordingly.”

2. 
Situation: Internal developers often had to run our log analysis tool multiple times on the same log bundle. However, the tool was using cached results after the first run, leading to stale data and incorrect outputs.

Task: I needed to build a refresh mechanism so developers could re-run the tool and get accurate results without manual intervention.

Action: I implemented a refresh functionality that intelligently bypassed the cache. To optimize performance, it reprocessed only the newly added or updated rules instead of running everything from scratch.

Result: This eliminated the manual step of deleting cache files, saving developer time and preventing errors. The tool became more reliable and user-friendly for internal teams.

# Insist on the Highest Standards
Q: Describe a time you improved quality in your work.

Answer:

Situation: The ticket update flow was manual and error-prone, leading to delays and inconsistent updates.

Task: I wanted to ensure every ticket was updated promptly and accurately.

Action: I automated the end-to-end ticket update process, including validations, comments, and transitions.

Result: This reduced ticket closure time by 30%, ensuring better tracking and quality control across triaged issues.

# Have Backbone; Disagree and Commit
Q: Tell me about a time you had a disagreement about a technical approach.

Answer:

Situation: In Panacea, there was conflict over whether to download logs (more fault-tolerant) or mount them (faster but less reliable).

Task: We had to choose an optimal method without compromising on either reliability or performance.

Action: I proposed a hybrid approach — download smaller logs and mount larger ones. I facilitated the discussion by focusing on trade-offs and validating assumptions.

Result: This resolved the conflict, improved performance, and retained fault tolerance. Everyone was aligned around a data-backed, balanced solution.

# Deliver Results
Q: Describe a high-impact project you delivered.

Answer:

Situation: The Panacea report generation process took 30+ minutes, delaying insights during critical incidents.

Task: Reduce the time taken without losing key data.

Action: I restructured the processing to run asynchronously, added Redis caching, and load balanced using HAProxy.

Result: Report generation time dropped from 30 minutes to under 15 minutes, and eventually reduced execution time to 2 minutes after all optimizations — improving incident response dramatically.

