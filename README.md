# WxIsDevTools - Helper Tools for Integration Server Development

The current repository offers Integration Server packages that are useful during development, without being necessary in production or CI/CD agents.

They are intended to be used during flow language code authoring.

## How to use

Link or copy the package(s) from the folder `./code/is-packages`.

For convenience, when changing the current packages only, use the provided run configuration to develop:

- go to `./run-configurations/is-dev-tools-dev-01`
- copy `example.env` into
- edit `.env` according to your local needs
  - Note: you may 
- start the project with `docker compose up`
  - Note: running in an attached window gives the standard output of the runtime, which may be useful during development
- run Software AG designer and connect to `http://localhost:${HOST_PORT_PREFIX}55` or preferably to `http://host.docker.internal:${HOST_PORT_PREFIX}55`
  - Note: after starting, try to run the test service `wx.localTest.IsDevTools:tracePipeline1` from Designer and notice the server.log and the runtime standard output.
- when finished with the code authoring, shut down the runtime with `docker compose down`
