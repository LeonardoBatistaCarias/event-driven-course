# event-driven-course

Run Axon Server with Docker

COMMAND: docker run --name axonserver -p 8024:8024 -p 8124:8124 -v "PATH/data":/data -v "PATH/eventdata":/eventdata -v "PATH/config":/config axoniq/axonserver

Obs: replace "PATH" with a path to a desired folder
