<?php

namespace App\Database\Migrations;

use CodeIgniter\Database\Migration;

class UserCred extends Migration
{
    public function up()
    {
        $this->forge->addField([
            'userid'    => [
                'type'          => 'INT',
                'constraint'    => 11,
                'auto_increment'    => True
            ],
            'username'    => [
                'type'          => 'VARCHAR',
                'constraint'    => 255
            ],
            'password'    => [
                'type'          => 'VARCHAR',
                'constraint'    => 255
            ],
            'isVerified'    => [
                'type'          => 'Bool',
                'default'       => FALSE,                
            ]
        ]);
        $this->forge->addKey('userid', true);
        $this->forge->createTable('userCred', true);
    }

    public function down()
    {
        $this->forge->dropTable('userCred');
    }
}
