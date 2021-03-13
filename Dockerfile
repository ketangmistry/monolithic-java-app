FROM maven:3.6.3-jdk-8
 
# copy the source tree and the pom.xml to our new container
COPY ./ ./

# Create a directory for the Debugger. Add and unzip the agent in the directory.
RUN mkdir /opt/cdbg && \
     wget -qO- https://storage.googleapis.com/cloud-debugger/compute-java/debian-wheezy/cdbg_java_agent_gce.tar.gz | \
     tar xvz -C /opt/cdbg

# Create a directory for the Profiler. Add and unzip the agent in the directory.
RUN mkdir -p /opt/cprof && \
  wget -q -O- https://storage.googleapis.com/cloud-profiler/java/latest/profiler_java_agent.tar.gz \
  | tar xzv -C /opt/cprof
 
# package our application code
RUN mvn clean package
 
# set the startup command to execute the jar
CMD ["java", "-agentpath:/opt/cdbg/cdbg_java_agent.so", "-Dcom.google.cdbg.module=frontend2", "-Dcom.google.cdbg.version=0.0.1", "-Dcom.google.cdbg.breakpoints.enable_canary=false", "-jar", "target/frontend2-0.0.1-SNAPSHOT.jar", "-agentpath:/opt/cprof/profiler_java_agent.so=-cprof_service=frontend2,-logtostderr,-minloglevel=2"]