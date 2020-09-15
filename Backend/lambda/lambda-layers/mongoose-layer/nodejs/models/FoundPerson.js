const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const foundPersonSchema = new Schema ({
  name : {
    type: String,
    required: true,
    maxlength: 60
  },
  mother_name : {
    type: String,
    maxlength: 20
  },
  national_number : {
    type: Number,
    maxlength: 17
  },
  gender : {
    type: String,
    enum: ['Male', 'Female']
  },
  birth_date : {
    type: Date
  },
  birth_place : {
    type: String,
    maxlength: 20
  },
  photo : { // will be a url for the person's photo as images are in s3
    type: String,
    maxlength:100
  },
  date_found : { // it's a timestamp (time & date)
    type: Date
  },
  status : {
    type: String,
    enum: ['Healthy','Wounded','Unconscious','Dead']
  },
  place_found : {
    type: String,
    maxlength:100
  },
  contact_number : { // the phone number of the poster of the 'found' post. 079-646-8922 format
    type: String,
    maxlength: 10,
    validate: {
      validator: function(v) {
        return /\d{3}-\d{3}-\d{4}/.test(v);
      },
      message: props => `${props.value} is not a valid phone number!`
    }
  }
});

module.exports = mongoose.model('FoundPerson',foundPersonSchema,"foundPeople");