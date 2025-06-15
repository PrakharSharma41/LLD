# Index

- [Describe a situation in which you had to arrive at a compromise with someone to move a project forward   ||   Tell me about a time you were working on a project where your co-collaborators were holding back the process.](#Describe-a-situation-in-which-you-had-to-arrive-at-a-compromise-with-someone-to-move-a-project-forward)
- [Tell me about a time that you had to balance competing business, team, or project priorities.   ||   Tell me about a time when you had to make a decision without all the information you needed.](#Tell-me-about-a-time-that-you-had-to-balance-competing-business-or-team-or-project-priorities)
- [Tell me about a time your first impression of a person or situation was incorrect.   ||   Tell me about a time you had a different point of view about how to solve a problem with someone on your team. || Have Backbone; Disagree and Commit    ||    time when you took an unpopular stance in a meeting](#Have-Backbone-Disagree-and-Commit)
- [ Share an example of a time when you were able to turn around the performance of an individual or team.   ||   Invent and Simplify    ||    proposed a new idea or process](#Invent-and-Simplify)
- [Tell me about a time when you had to make a tradeoff that involved potentially causing pain for a user or customer.  ||  Tell me about a time when you advocated for the customer, despite opposition.](#Tell-me-about-a-time-when-you-had-to-make-tradeof-that-involved-causing-pain-for-a-user-or-customer)
- [Ownership    ||    time you took ownership of a problem others ignored](#Ownership)
- [Bias for Action    ||    made a decision with incomplete data || worked against tight deadlines](#Bias-for-Action)
- [Dive Deep    ||    problem you solved by digging into the data](#Dive-Deep)
- [Learn and Be Curious    ||    something new you learned to solve a problem](#Learn-and-Be-Curious)
- [Hire and Develop the Best / Teamwork    ||    support teammates who are struggling](#Hire-and-Develop-the-Best-/-Teamwork)
- [Customer Obsession    ||    time you went above and beyond for a customer](#Customer-Obsession)
- [Insist on the Highest Standards    ||    time you improved quality in your work](#Insist-on-the-Highest-Standards)
- [Deliver Results](#Deliver-Results)
- [Give an example of a tough or critical piece of feedback you received](#Give-an-example-of-a-tough-or-critical-piece-of-feedback-you-received)
- [time when you took on something significant outside your area of responsibility](#time-when-you-took-on-something-significant-outside-your-area-of-responsibility)
- [Sacrificing Short-term for Long-term](#Sacrificing-Short-term-for-Long-term)
- [Tell me about a time a customer wanted one thing, but you felt they needed something else.](#Tell-me-about-a-time-a-customer-wanted-one-thing,-but-you-felt-they-needed-something-else.)
- [time when you didn't think you were going to meet the commitments you promised](#time-when-you-didn't-think-you-were-going-to-meet-the-commitments-you-promised)


# Describe a situation in which you had to arrive at a compromise with someone to move a project forward
**Situation:**
We were integrating RDM deployment logs into our internal debugging tool. Our standard approach of mounting logs was blocked by the security and deployment teams due to compliance risks.

**Task:**
My goal was to give developers access to logs without violating security policies, ensuring both usability and compliance.

**Action:**
I built a download-based logging system with short-lived tokens, RBAC, and audit trails.
Later, we hit a parsing issue: logs came with random filenames like deploymentid_1.txt, which broke our parser rules. Since we couldn’t store or predict deployment IDs securely, I chose to rename all logs to rdm_logs.txt at download time, ensuring consistent parsing.

**Result:**
The approach caused minor friction — loss of raw filenames and an extra download step — but it ensured secure, reliable access to logs.
We unblocked RDM integration, and the model scaled to other restricted deployments as well.

---

# Tell me about a time that you had to balance competing business or team or project priorities

**Situation:**
I was on production on-call duty for our team when a high-priority customer issue came in. At the same time, I was also working on a critical feature that was scheduled for a demo day just a couple of days away.

**Task:**
I needed to handle the on-call incident quickly to restore service for the customer, while also ensuring my feature was ready in time for the demo, which was important from a business and visibility perspective.

**Action:**
I prioritized the on-call issue first, since it was customer-impacting and time-sensitive. I worked on stabilizing the service and ensured immediate relief for the customer.

Once the situation was under control, I coordinated with a teammate and asked them to take over the detailed RCA and follow-up actions.

With that coverage in place, I switched back to my feature development and focused on wrapping it up. Although I lost some time, I adjusted my schedule and managed to finish the work before the demo day.

**Result:**
The customer issue was resolved quickly, with no SLA breach.
My teammate completed the RCA, keeping the incident loop tight.
The feature was delivered in time, and the demo went smoothly.

---

# Have Backbone Disagree and Commit
Q: Tell me about a time you had a disagreement about a technical approach.<br>
Q: tell me about a time when you took an unpopular stance in a meeting with peers and your leader and you were the outlier<br>
Answer:<br>
1. 
**Situation:**
We were building a log analysis system that required fast and scalable text search capabilities across large volumes of machine-generated logs.

**Task:**
The team was inclined to use Elasticsearch, a popular and proven search engine. However, I believed it might be overkill for our use case and wanted to explore alternatives.

**Action:**
I suggested using Manticore Search, which is lighter weight and simpler to manage. My teammates initially disagreed, citing Elasticsearch’s wide adoption and community support. I understood their concerns, but I benchmarked both options using our real log samples.<br> I demonstrated that Manticore could handle our scale—millions of logs—efficiently on a single 32-core VM, without needing to set up and manage a distributed cluster.

**Result:**
The team agreed to adopt Manticore. We achieved high performance with lower infrastructure overhead and reduced operational complexity. This decision saved compute resources and made our system easier to maintain in the long run.

2. 
**Situation:** In our internal tool, there was conflict over whether to download logs (more fault-tolerant) or mount them (faster but less reliable).

**Task:** We had to choose an optimal method without compromising on either reliability or performance.

**Action:** I proposed a hybrid approach — download smaller logs and mount larger ones. I facilitated the discussion by focusing on trade-offs and validating assumptions.

**Result:** This resolved the conflict, improved performance, and retained fault tolerance. Everyone was aligned around a data-backed, balanced solution.

---

# Invent and Simplify
Q: Give an example of a time you proposed a new idea or process. What was the response?<br>
Answer:<br>

1. 
**Situation:**
Our team was working on multiple services, and we noticed some recurring issues slipping through PR reviews, including security concerns and breaking changes. While our code reviews were diligent, they were not always consistent, especially under tight timelines.

**Task:**
I wanted to improve code quality and review efficiency, while reducing human errors. The goal was to raise the overall performance bar for the team without adding more manual effort.

**Action:**
Our company encourages using AI tools like GitHub Copilot, so I proposed a change to make Copilot a mandatory reviewer in our repository.

I set it up to trigger automatically on every PR.

It runs static checks and provides feedback within seconds.

We configured it to highlight potential security flaws, bad patterns, and risky changes before human review.

**Result:**
It caught multiple security issues and breaking changes early in the PR lifecycle.

Review cycles became faster and more reliable.

Developers started to self-correct before pushing PRs, raising the quality bar.


2. 
**Situation:** Internal tool has to be run by developers manually and wait for its result to come

**Task:** Running our internal analysis tool by default as soon as a ticket is raised, provide analysis and 
update in ticket itself.

**Action:** I automated the entire flow of sending and new ticket to our channel, it is intercepted by my script and trigger internal tool asynchronously for any number of tickets and update the ticket

**Result:** This reduced the manual procedure to update the flow.

3. 
**Situation:** As a part of the central team, we often received tickets that didn’t belong to us because reporters randomly assigned issues without proper triage.

**Task:** This created noise and unnecessary workload. I wanted to reduce these false positives and ensure correct ownership from the start.

**Action:** I proactively discussed this problem with my manager and designed a standardized procedure with clear documentation. It guided reporters to identify the appropriate team before assigning tickets.

**Result:** This significantly reduced false positives and improved triaging accuracy, saving time and increasing team productivity.

4. 
**Situation:**
Our internal log analysis tool was generating many false positives when matching known issues from logs, which led to wasted debugging effort and delayed root cause identification.

**Task:**
We needed to significantly improve the tool’s accuracy so teams wouldn't chase already resolved or irrelevant issues.

**Action:**
I investigated the false positives and analyzed the recently added rules. I discovered that many patterns were too generic or incorrectly written due to user negligence. I built a parser to help users write better rules by suggesting improvements. Additionally, I enhanced the rule engine to support filtering based on log level and source log file name, adding more context to each match.

**Result:**
These changes reduced false positives by 40%, improved detection accuracy, and made the triage process significantly more reliable.

---
Tell-me-about-a-time-when-you-had-to-make-tradeof-that-involved-causing-pain-for-a-user-or-customer
# Tell me about a time when you had to make tradeof that involved causing pain for a user or customer
**Situation:**
We had recently integrated AI/ML-based anomaly detection into our tool using SupportGPT, an internal LLM model. While this significantly improved the accuracy of issue detection in reports, it also increased report generation time, leading to user complaints about slower feedback loops.

**Task:**
Our goal was to deliver intelligent insights without compromising the user experience. There was tension between retaining the powerful new feature and restoring the responsiveness users expected from the system.

**Action:**
After discussions with product and engineering managers, I proposed a solution where we’d decouple AI processing from the main report generation pipeline. I introduced a “partial complete” status for reports — the report would be made available to users once the core sections were ready, while the AI-powered anomaly detection would continue to run in the background. Once the AI part completed, we’d notify the user with the updated insights.

**Result:**
This solution allowed us to preserve the value of AI insights while significantly improving perceived performance. Users appreciated getting faster access to reports and were still able to benefit from the anomaly detection asynchronously. Complaint volume dropped, and adoption of the AI feature remained strong.

---

# Ownership
Q: Tell me about a time you took ownership of a problem others ignored.
Answer:

**Situation:** In our internal tool, our log analysis tool processed all levels of logs (info, warning, fatal), leading to long report generation times and false positives.

**Task:** I aimed to optimize the log parsing process to prioritize critical information and reduce noise.

**Action:** I proposed a prioritized approach where we parse warning and fatal logs first and defer info logs unless relevant. I collaborated with the team to implement this logic.

**Result:** We achieved a 50% reduction in report generation time and significantly improved the quality of insights by focusing on what mattered most.

---

# Bias for Action
Q: Tell me about a time you made a decision with incomplete data.<br>
Q: Tell me about a time when you have worked against tight deadlines and didn't have the time to consider all options before making a decision. What approach did you take?<br>
Answer:<br>

1. 
**Situation:**
At Nutanix, we faced a critical production issue where one of our key backend services responsible for VM CRUD operations was intermittently crashing due to OutOfMemoryErrors. This happened just 30 minutes before peak business hours, and failure meant customers could no longer create, delete, or update VMs.

**Task:**
My top priority was to prevent further service crashes before customers noticed. We couldn't wait for a full root cause analysis — we had to stabilize the system immediately while ensuring no data corruption or operation loss.

**Action:**
I quickly scanned JVM heap dumps and GC logs. I noticed frequent full GCs with negligible memory recovery — a typical sign of a memory leak. While reviewing heap objects, I saw an unusually large number of retained HashMap<Key, SoftReference<VMData>> entries, where the keys were VM IDs.

Digging deeper, I found that:

These keys (VM IDs) were still strongly referenced in a deleted VM tracking list, even though the actual VMs had been deleted.
So even though the values were wrapped in SoftReference, the keys weren’t eligible for GC.
As a result, our map holding VM metadata kept growing — holding on to dead entries.
With little time to experiment, I made two tactical decisions:
1. Temporarily increased JVM heap size to buffer memory pressure.
2. Disabled a recently released feature that cached deleted VMs for audit — which was indirectly keeping those VM IDs alive.

**Result:**
The service stabilized within 20 minutes, and we avoided any customer impact during business hours. Post-incident, I collaborated with the owning team to:
1. Switch the internal cache to use WeakReference<K> for VM IDs and
2. Clean up the parallel tracking structure to allow GC of deleted VM entries.


2. 
**Situation:** During a company hackathon, we aimed to automate the ticket update flow, but we didn’t have complete data around all Jira ticket transition conditions.

**Task:** Despite the uncertainty, we wanted to deliver an MVP that could handle the most common flows.

**Action:** I analyzed ticket patterns from existing logs, inferred the key transitions, and led the team in building a working end-to-end solution.

**Result:** The prototype worked well in most cases and was awarded Category Winner, validating our hypothesis and speeding up ticket updates by 30%.

---

# Dive Deep
Q: Describe a problem you solved by digging into the data.<br>

Answer:<br>

**Situation:**
Our internal log analysis tool was generating many false positives when matching known issues from logs, which led to wasted debugging effort and delayed root cause identification.

**Task:**
We needed to significantly improve the tool’s accuracy so teams wouldn't chase already resolved or irrelevant issues.

**Action:**
I investigated the false positives and analyzed the recently added rules. I discovered that many patterns were too generic or incorrectly written due to user negligence. I built a parser to help users write better rules by suggesting improvements. Additionally, I enhanced the rule engine to support filtering based on log level and source log file name, adding more context to each match.

**Result:**
These changes reduced false positives by 40%, improved detection accuracy, and made the triage process significantly more reliable.

---

# Learn and Be Curious
Q: Tell me about something new you learned to solve a problem.<br>

Answer:<br>
1. 
**Situation:**
Our internal tool relied heavily on text-based log search and parsing, we needed to explore ways

**Task:**
I needed to find efficient alternatives for log searching and parsing that would improve speed and fault tolerance.

**Action:**
I proactively explored new technologies beyond our usual stack. I learned about Manticore Search, a lightweight alternative to Elasticsearch, and studied its integration model. I also evaluated various parsing tools but realized that building custom parsers would give us better control and performance. I took time to understand log structures, indexing techniques, and how to optimize query speed.

**Result:**
By adopting Manticore and implementing custom parsers, I achieved significantly faster log processing and improved the system’s fault tolerance. This not only solved the performance issues but also expanded our team’s knowledge base on search technologies.

2. 
**Situation:** I initially joined the team as a node js backend engineer, but key services for our ticket triaging and dials were backend-heavy, involving Java, Python, and Kubernetes.

**Task:** To be effective, I had to quickly ramp up on backend technologies and Kubernetes.

**Action:** I self-learned backend flows, started debugging services in Java/Python, and completed the CKAD certification to understand Kubernetes better.

**Result:** This enabled me to debug cross-service issues confidently and drive improvements in our triaging pipeline, especially during high-pressure release windows.

---

# Hire and Develop the Best / Teamwork
Q: How do you support teammates who are struggling?<br>

Answer:<br>

If someone on my team is lagging behind, I first try to understand why — skill gaps, lack of clarity, or personal challenges.

If it’s time-critical, I let them shadow a senior teammate to learn quickly and contribute right away. If we have time, I assign them a self-contained module to own, providing guidance and regular syncs.

This approach helps them feel supported, builds confidence, and ensures we deliver as a team.

**Situation:**
A new teammate was struggling with understanding our internal log analysis architecture and was missing deadlines.

**Task:**
Although not a mentor officially, I decided to step in and help onboard him faster.

**Action:**
I created a simplified architecture diagram, walked him through workflows over multiple sessions, and paired on debugging issues together.

**Result:**
He quickly became productive and later contributed major improvements to the tool. He appreciated the support and later credited it as key to his onboarding.

---

# Customer Obsession
Q: Tell me about a time you went above and beyond for a customer.<br>

Answer:<br>
1. 
**Situation:**
At Nutanix, we faced a production issue where a key service was intermittently crashing, affecting customer access. It was early morning, and we had less than 30 minutes before peak business hours.

**Task:**
I had to act quickly to stabilize the service without waiting for a full root cause analysis — the priority was to avoid business-hour impact and customer escalations.

**Action:**
I immediately scanned heap dumps and JVM GC logs. I noticed full GCs were not freeing up memory — a clear sign of a memory leak. With little time to experiment, I made two quick decisions:

Increased the JVM heap as a temporary buffer.

Disabled a newly released feature that was showing correlation with the memory issue.

I chose these based on recent deployment history and memory metrics — even though I didn’t have conclusive proof yet, it was the safest risk-balanced path.

**Result:**
The service stabilized within 30 minutes and customer impact was avoided. After that, I worked with the owning team to:

Trace the leak to a flaw in cache eviction logic.

2. 
**Situation:** Internal developers often had to run our log analysis tool multiple times on the same log bundle. However, the tool was using cached results after the first run, leading to stale data and incorrect outputs.

**Task:** I needed to build a refresh mechanism so developers could re-run the tool and get accurate results without manual intervention.

**Action:** I implemented a refresh functionality that intelligently bypassed the cache. To optimize performance, it reprocessed only the newly added or updated rules instead of running everything from scratch.

**Result:** This eliminated the manual step of deleting cache files, saving developer time and preventing errors. The tool became more reliable and user-friendly for internal teams.

---

# Insist on the Highest Standards
Q: Describe a time you improved quality in your work.<br>

similar to 
[Give an example of a tough or critical piece of feedback you received](#Give-an-example-of-a-tough-or-critical-piece-of-feedback-you-received)<br>
Answer:<br>

**Situation:** The ticket update flow was manual and error-prone, leading to delays and inconsistent updates.

**Task:** I wanted to ensure every ticket was updated promptly and accurately.

**Action:** I automated the end-to-end ticket update process, including validations, comments, and transitions.

**Result:** This reduced ticket closure time by 30%, ensuring better tracking and quality control across triaged issues.

---


# Deliver Results
Q: Describe a high-impact project you delivered.<br>
The system processes ingested log bundles associated with specific Jira tickets, identifies anomalies in
system metrics and log entries within defined timeframes<br>
and leverages SupportGPT to generate contextual summaries, potential Root Cause Analysis (RCA), and retrieve relevant historical issues. The primary objective is to significantly reduce
troubleshooting time for support engineers by providing Al-driven insights derived from extensive log and metric data.<br>

Answer:<br>

**Situation:** Our internal tool report generation process took 30+ minutes, delaying insights during critical incidents.

**Task:** Reduce the time taken without losing key data.

**Action:** I restructured the processing to run asynchronously, added Redis caching, and load balanced using HAProxy.

**Result:** Report generation time dropped from 30 minutes to under 15 minutes, and eventually reduced execution time to 2 minutes after all optimizations — improving incident response dramatically. Early closures of approx 25% of tickets.

---

# Give an example of a tough or critical piece of feedback you received

1. 
**Situation:**
I received feedback that while my code was efficient, my documentation and handoffs lacked detail, making it hard for others to maintain or build upon.

**Task:**
I needed to improve the clarity and completeness of my handoffs and knowledge sharing.

**Action:**
I started writing clear READMEs, added inline comments, and began recording short video walkthroughs for major flows. I also created onboarding docs for new members using the tool.

**Result:**
The feedback loop improved — peers found it easier to work with my code. One of my documents became the standard reference in our internal wiki.


2. 

**Situation:**
At OLX, I was part of the Platform Engineering team where we worked primarily with Go, Kubernetes, and GitLab CI/CD pipelines.

**Task:**
My manager advised me to strengthen my knowledge of Kubernetes and CI/CD pipelines so I could contribute more effectively to deployment workflows and platform automation tasks.

**Action:**
I took this feedback seriously and enrolled in the CKAD (Certified Kubernetes Application Developer) certification to deepen my understanding. I also studied GitLab pipelines in detail and practiced by setting up dummy services with full deployment automation.

**Result:**
Not only did this help me contribute more effectively at OLX, but the skills transferred well to my current role at Nutanix. I’ve been able to independently set up CI/CD pipelines and dockerize our internal tools for seamless deployment — reducing setup time and improving reliability.


# time when you took on something significant outside your area of responsibility
[similar to Ownership](#Ownership)<br>


---

# Sacrificing Short-term for Long-term
**Situation:**
We had a quick fix to reduce false positives in our issue detection engine, which could be deployed immediately.

**Task:**
I suggested we instead invest time in creating a parser suggestion engine and improve rule structures, which would take longer but offer better precision.

**Action:**
Despite pressure for a quick fix, I got buy-in to build the better foundation. I created the suggestion parser and redesigned rules with log-level and file-source context.

**Result:**
False positives dropped by 40%, and future rule creation became easier and more accurate. It delayed short-term gains but created long-term value.

---

# Tell me about a time a customer wanted one thing, but you felt they needed something else
similar to [Customer Obsession](#Customer-Obsession)<br>
**Situation:**
A customer wanted us to disable log analysis caching so they could rerun analysis each time with new rules.

**Task:**
Disabling the cache would degrade performance for all users. I had to find a better alternative.

**Action:**
I proposed and implemented a "refresh" feature that invalidates the cache selectively and reruns the analysis only on updated rules.

**Result:**
The customer’s problem was solved without hurting performance. This solution became the recommended approach for all teams.

---

# time when you didn't think you were going to meet the commitments you promised
**Situation:**
I was implementing a refresh functionality for our internal log analysis tool at Nutanix. Initially, the tool reprocessed all the rules every time a refresh was triggered, which was inefficient but functional.

**Task:**
I had committed to delivering the refresh feature by a specific date. However, during implementation, I realized that processing only the newly added rules would make the system much more efficient. This optimization required changes to the schema and the way rules were stored and retrieved.

**Action:**
I quickly realized that the optimization would take additional time and could put the original timeline at risk. I informed my manager and stakeholders, explaining the trade-off: either deliver the basic version on time or take a few extra days to build a smarter, more scalable solution. I demonstrated how the optimized path would improve performance and reduce future compute cost.

**Result:**
They agreed to the delay. I delivered the optimized refresh functionality with schema changes, which reduced processing time and eliminated the need for full reprocessing. The outcome was more efficient, maintainable, and scalable — and saved the team manual overhead in the long run.


Internal Tool:
The system processes ingested log bundles associated with specific Jira tickets, identifies anomalies in
system metrics and log entries within defined timeframes<br>
and leverages SupportGPT to generate contextual summaries, potential Root Cause Analysis (RCA), and retrieve relevant historical issues. The primary objective is to significantly reduce
troubleshooting time for support engineers by providing Al-driven insights derived from extensive log and metric data.<br>

1. used by sres,qas,dev teams for triaging issues.<br>
2. shows system metrics and application metrics.<br>
3. search, correlate logs based on some patterns and identify issues.<br>
4. leverage ai like supportGPT tools to figure out relevant kbs for a problem.<br>