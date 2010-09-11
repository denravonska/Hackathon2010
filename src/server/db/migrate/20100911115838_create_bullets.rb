class CreateBullets < ActiveRecord::Migration
  def self.up
    create_table :bullets do |t|
      t.integer :parent_id
      t.float :x
      t.float :y
      t.float :heading
      t.float :speed
      t.integer :hp

      t.timestamps
    end
  end

  def self.down
    drop_table :bullets
  end
end
