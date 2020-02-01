./gradlew clean ;
./gradlew build ;
docker build -f Dockerfile -t anthemorg/process_record_events_test .
docker tag anthemorg/process_record_events_test private-registry.cortex.insights.ai/anthemorg/process_record_events_test
docker push private-registry.cortex.insights.ai/anthemorg/process_record_events_test
cortex actions deploy anthemorg/process_record_events_test --actionType daemon --docker private-registry.cortex.insights.ai/anthemorg/process_record_events_test --port '9092'
cortex skills save --yaml skill.yaml