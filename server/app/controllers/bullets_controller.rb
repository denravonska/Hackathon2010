class BulletsController < ApplicationController
  # GET /bullets
  # GET /bullets.xml
  def index
    @bullets = Bullet.all

    respond_to do |format|
      format.html # index.html.erb
      format.xml  { render :xml => @bullets }
      format.json  { render :json => @bullets }      
    end
  end

  # POST /bullets
  # POST /bullets.xml
  def create
    @bullet = Bullet.new(params[:bullet])
    @bullet.x = params[:x]
    @bullet.y = params[:y]
    @bullet.heading = params[:heading]
    @bullet.speed = params[:speed]
    @bullet.hp = params[:hp]
    
    respond_to do |format|
      if @bullet.save
        format.json  { render :json => @bullet, :status => :created }
      else
        format.json  { render :json => @bullet, :status => :error }
      end
    end
  end


  # DELETE /bullets/1
  # DELETE /bullets/1.xml
  def destroy
    @bullet = Bullet.find(params[:id])
    @bullet.destroy

    respond_to do |format|
      format.json  { render :json=>@bullet, :status => :ok }
    end
  end
end
