const knex= require('knex');
const config = require('../../knexfile');
const md5 = require('md5');

const dbClient=knex(config);

var jwt = require('jsonwebtoken');
const tokenconfig = require('../../config');


function loginHandler(req,res){
    let username = req.body.username;
    let password = req.body.password;
    let pwd=md5(password);
    console.log(username);
    console.log(password);

    dbClient('users').where({ username: username})
  .select('password')
  .then(function(result) {
    if (!result || !result[0])  {
        return res.status(400).json({
            message: 'Invalid Username'
          });
    }
    var pass = result[0].password;
    if (pwd === pass) {
        let token = jwt.sign({username: username},
            tokenconfig.secret,
            { expiresIn: '24h' // expires in 24 hours
            }
          );
          // return the JWT token for the future API calls
          return res.json({ success: true, token:token });
    } else {
        return res.status(400).json({
            message: 'Incorrect username or password'
          });    }
  })
  .catch(function(error) {
    console.log(error);
  });



   
}

function signupHandler(req,res){

  //console.log(req.body);

  let first_name=req.body.first_name;
  let last_name=req.body.last_name;
  let username = req.body.username;
  let password = req.body.password;
  let pwd=md5(password);


  dbClient('users').insert({
    first_name:first_name,last_name:last_name,username:username,password:pwd
  })
.then(function(result) {
  return res.json({
    status: "success",
  });
})
.catch(function(error) {
  console.log(error);
});

}



module.exports = {
    'login' : loginHandler,
    'signup':signupHandler
 }