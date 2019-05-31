exports.up = async function(knex, Promise) {
    await knex.schema.hasTable('users');
    
    return await knex.schema.createTable('users',table =>{

    table.increments('id').unsigned().primary();
    table.string('first_name');
    table.string('last_name');
    table.string('username');
    table.string('password');
});

};

exports.down = function(knex, Promise) {
    knex.schema.dropTable('users');

  
};
