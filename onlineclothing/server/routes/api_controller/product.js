const knex= require('knex');
const config = require('../../knexfile');

const dbClient=knex(config);

const multer = require('multer');
const path =require('path');

var storage = multer.diskStorage({
    destination: (req, file, cb) => {
      cb(null, './public/uploads/')
    },
    filename: (req, file, cb) => {
      // console.log(file.originalname);
      // console.log(file.fieldname);
      let ext=path.extname(file.originalname);
      cb(null, file.fieldname + '-' + Date.now() + '.jpg')
    }
});
var upload = multer({storage: storage}).single('imageFile');





function getHandler(req,res){
   
    dbClient('products').select('*')
  .then(function(result) {
    return res.json(result);  
  })
  .catch(function(error) {
    console.log(error);
  });
  
}

function createHandler(req,res){
  let name=req.body.name;
  let price=req.body.price;
  let description = req.body.description;
  let image = req.body.image;

  dbClient('products').insert({
    name:name,price:price,description:description,image:image
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

function uploadHandler(req,res){
    upload(req,res,function(err) {
        if(err) {
            return res.end("Error uploading file.");
        }
        console.log(req.file);
        res.json(req.file);
    });

}




module.exports = {
    'get' : getHandler,
    'create':createHandler,
    'upload':uploadHandler
 }