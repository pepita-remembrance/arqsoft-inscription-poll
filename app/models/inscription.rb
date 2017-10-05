class Inscription < ApplicationRecord
  belongs_to :student
  belongs_to :course
  belongs_to :classroom
end
