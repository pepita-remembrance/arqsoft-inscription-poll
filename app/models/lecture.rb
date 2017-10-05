class Lecture < ApplicationRecord
  belongs_to :teacher
  belongs_to :course
  belongs_to :classroom
end
