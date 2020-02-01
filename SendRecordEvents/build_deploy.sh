./gradlew clean ;
./gradlew build ;
docker build -f Dockerfile -t anthemorg/send_user_events_test .
docker tag anthemorg/send_user_events_test private-registry.cortex.insights.ai/anthemorg/send_user_events_test
docker push private-registry.cortex.insights.ai/anthemorg/send_user_events_test
cortex actions deploy anthemorg/send_user_events_test --actionType daemon --docker private-registry.cortex.insights.ai/anthemorg/send_user_events_test --port '9091'
cortex skills save --yaml skill.yaml