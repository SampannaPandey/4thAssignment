const path = require('path');

module.exports ={

    client:'mysql',
    connection:{
        host:'localhost',
        user:'root',
        password:'0922',
        database:'android_clothing'

    
},
migrations: {
    tableName:'migrations',
    directory: path.resolve(__dirname,'./migrations'),
},
useNullAsDefault:true
};
