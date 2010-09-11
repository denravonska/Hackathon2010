class CreatePlayers < ActiveRecord::Migration
  def self.up
    create_table :players do |t|
      t.integer :id
      t.float :x
      t.float :y

      t.timestamps
    end
  end

  def self.down
    drop_table :players
  end
end
