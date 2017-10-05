module InscriptionsHelper
  def inscriptions_path(extras={})
    "/inscriptions/index#{".#{extras[:format]}" unless extras[:format].nil?}"
  end

  def new_inscription_path
    '/inscriptions/new'
  end

  def edit_inscription_path(inscription)
    "/inscriptions/#{inscription.__id__}/edit"
  end
end
