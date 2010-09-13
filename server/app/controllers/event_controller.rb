class EventController < ApplicationController

  def index
    @players = Player.all
    @bullets = Bullet.all
    @events = [@players, @bullets]
    respond_to do |format|
      format.json  { render :json => @events, :header=>'application/json' }
    end
  end
   
  def update_player_position
    @player = Player.find(params[:player_id])
    if params[:x] and params[:y]
      @player.x = params[:x]
      @player.y = params[:y]
      @player.heading = params[:heading]
      @player.hp = params[:hp]
      @player.speed = params[:speed]

      @player.save
    end
    respond_to do |format|
      format.json  { render :json => @player }
    end    
  end
  
  def update_bullet_position
    @bullet = Bullet.find(params[:bullet_id])
    if params[:x] and params[:y]
      @bullet.x = params[:x]
      @bullet.y = params[:y]
      @bullet.heading = params[:heading]
      @bullet.hp = params[:hp]
      @bullet.speed = params[:speed]
      @bullet.save
    end
    respond_to do |format|
      format.json  { render :json => @bullet }
    end    
  end
  
end
