# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_dfserver_session',
  :secret      => '663837e1e9ee629e4acb5ecf4f3fb5a032e8a3be6f240756407773b114796edd5190eff1704c4c141a5296f450e73b170005876ddad94c982a1addce6ca4323b'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store
