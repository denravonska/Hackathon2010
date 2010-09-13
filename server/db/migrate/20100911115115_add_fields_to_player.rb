class AddFieldsToPlayer < ActiveRecord::Migration
  def self.up
      add_column :players, :speed, :float
      add_column :players, :heading, :float
      add_column :players, :hp, :integer

  end

  def self.down
    remove_column :players, :speed
    remove_column :players, :heading
    remove_column :players, :hp
  end
end
