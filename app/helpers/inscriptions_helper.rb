module InscriptionsHelper
  def inscriptions_path(extras={})
    "/inscriptions/index#{".#{extras[:format]}" unless extras[:format].nil?}"
  end

  def inscription_path(inscription)
    "/inscriptions/#{inscription.__id__}"
  end

  def new_inscription_path
    '/inscriptions/new'
  end

  def edit_inscription_path(inscription)
    "#{inscription_path inscription}/edit"
  end
end
