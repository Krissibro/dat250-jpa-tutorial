# Expass 7

Everything works!

I had no issues with changing the database solution from h2 to postgres and all tests pass.

Making my frontend/backend application from expass 2 and 3 was a bit more tricky though.
I decided to put both folders in the same project for clarity.
Because of this, the process of dockerizing my application was a bit more involved.

I mainly had issues with getting the backend and frontend to communicate with each other 
and getting the right ports to work both within the docker container and locally on the machine.

I had to add a specific url `http://backend:8080` for the frontend to communicate with the backend into the vite config.
And I also added a script to the frontend which makes it wait for the backend before running.

But, in the end running the docker container, both backend and frontend now works just fine.

[Link to frontend/backend commit](https://github.com/Krissibro/DAT250-Sploinkyboink/commit/eb3886dcf71f572f8ff5c5706df6fcb4c20784dc)

