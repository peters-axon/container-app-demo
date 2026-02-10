FROM axonivy/axonivy-engine:12.0.11

COPY --chown=1000:0 target/*.zip /ivy/deploy/container-app-demo/

# RUN ls -lRa /ivy/d* 


