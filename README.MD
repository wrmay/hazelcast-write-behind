# Overview

This starter generates a simple Hazelcast server and client plus a docker compose based environment containing the
following services:

- Cluster A
  - __memberA1__, a Hazelcast server (1 of 2 in cluster A)
  - __memberA2__, a Hazelcast server (2 of 2 in cluster A)
  - __clientA__, a Hazelcast client that reads from and writes to cluster A.


- Cluster B
  - __memberB1__, a Hazelcast server (1 of 2 in cluster B)
  - __memberB2__, a Hazelcast server (2 of 2 in cluster B)
  - __clientB__, a Hazelcast client that reads from and writes to cluster B.
  
- hazelcast-mancenter 



