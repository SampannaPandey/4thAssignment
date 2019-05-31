exports.up = async function(knex, Promise) {
    await knex.schema.hasTable('products');
    
    return await knex.schema.createTable('products',table =>{

    table.increments('id').unsigned().primary();
    table.string('name');
    table.string('price');
    table.string('description');
    table.string('image');
});

};

exports.down = function(knex, Promise) {
    knex.schema.dropTable('products');

  
};
