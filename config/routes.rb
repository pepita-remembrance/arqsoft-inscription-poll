Rails.application.routes.draw do
  get 'inscriptions/index'

  get 'inscriptions/new'

  root 'inscriptions#index'
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
