const mongoose = require('mongoose');
const Schema = mongoose.Schema;

console.log('here');
const missingPersonSchema = new Schema ({
  name_arabic : {
    type: String
  },
  name_english : {
    type: String
  },
  mother_name : {
    type: String
  },
  national_number : {
    type: String
  },
  gender : {
    type: String
  },
  birth_date : {
    type: Date
  },
  birth_place : {
    type: String
  },
  photo : { // will be a url for the person's photo as images are in s3
    type: String
  },
  missing_since : {
    type: Date
  },
  status : {
    type: String
  }
});

module.exports = mongoose.model('MissingPerson',missingPersonSchema,"missingPeople");