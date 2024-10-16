# Expass 7

Everything works!

I had no issues with changing the database solution from h2 to postgres and all tests pass.

I ran the postgres docker image using this command, mapping it to port 5432
```
docker run --name my-postgres -e POSTGRES_PASSWORD=bazinga -p 5432:5432 -d postgres
```
After I ran the container, I set up the connection to the database by [updating the persistence file](https://github.com/Krissibro/dat250-jpa-tutorial/commit/3a8d5a52a4a18b07a8634f7b57b05f2c2994c420#diff-c0dfa6bc7a8685217f70a860145fbdf416d449eaff052fa28352c5cec1a98c06R22)

And all tests pass when I run them after this.

------

Making my frontend/backend application from expass 2 and 3 was a bit more tricky though.
I decided to put both folders in the same project for clarity.
Because of this, the process of dockerizing my application was a bit more involved.

I mainly had issues with getting the backend and frontend to communicate with each other 
and getting the right ports to work both within the docker container and locally on the machine.

I had to add a specific url `http://backend:8080` into the vite config for the frontend to communicate with the backend inside the container.
And I also added a script to the frontend which makes it wait for the backend before running.

But, in the end running the docker container, both backend and frontend now works just fine.

[Link to commit containing the DOCKERFILES I created](https://github.com/Krissibro/DAT250-Sploinkyboink/commit/eb3886dcf71f572f8ff5c5706df6fcb4c20784dc)

