# MultiThreading-UseCases
This repository contains 4 Multi Threading java classes as below
- In first one I have demonstrated the use of Reentrant lock where 10000 threads are trying to add an integer -1 in a queue and how without any locks multiple threads access the ame part of queue and hence at the end the size of queue is not 10000 but less than that. After adding Reentrant lock for it, the queue size becomes 10000 which is what is desired and which helps us to understand how concurrent queues behave.
- In other 3 java classes I have demonstrated an usecase where I am trying to find out prime numbers upto 100000000 and how it works with no threads, batch of threads and threads with fairness properties.
