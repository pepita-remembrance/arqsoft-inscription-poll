# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

students = Student.create([
                              {fullname: 'Emanuel Dubor'},
                              {fullname: 'Nicolas Leutwyler'},
                              {fullname: 'Obi Wan Kenobi'},
                              {fullname: 'Darth Vader'}
                          ])
teachers = Teacher.create([
                              {fullname: 'Leonardo Volinier'},
                              {fullname: 'Fidel'}
                          ])
classrooms = Classroom.create([
                                  {number: '331'},
                                  {number: '37B'},
                                  {number: '60'}
                              ])
courses = Course.create([
                            {title: 'ArqSoft'},
                            {title: 'Estructuras'}
                        ])
lectures = Lecture.create([
                              {teacher: teachers[0], course: courses[0], classroom: classrooms[0]},
                              {teacher: teachers[1], course: courses[1], classroom: classrooms[1]},
                              {teacher: teachers[1], course: courses[1], classroom: classrooms[2]}
                          ])
incriptions = Inscription.create!([
                                     {student: students[0], course: courses[0], classroom: classrooms[0]},
                                     {student: students[1], course: courses[0], classroom: classrooms[0]},
                                     {student: students[2], course: courses[0], classroom: classrooms[0]},
                                     {student: students[2], course: courses[1], classroom: classrooms[1]},
                                     {student: students[3], course: courses[1], classroom: classrooms[2]}
                                 ])