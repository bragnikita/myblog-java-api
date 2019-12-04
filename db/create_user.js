// var user = ""
// var pwd = ""
// var database = ""
db.getSiblingDB(database).createUser({
    user: user,
    pwd: pwd,
    roles: [ "readWrite" ],
});