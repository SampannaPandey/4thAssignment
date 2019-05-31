var express = require('express');
var router = express.Router();



const product=require('../api_controller/product');
router.get('/',product.get);
router.post('/create',product.create);

router.post('/upload',product.upload);

module.exports = router;
