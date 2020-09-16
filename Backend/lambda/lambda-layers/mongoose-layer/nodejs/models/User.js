const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const userSchema = new Schema ({
  phone_number : {
    type: String,
    maxlength: 10,
    required:true,
    validate: {
      validator: function(v) {
        return /\d{10}/.test(v);
      },
      message: props => `${props.value} is not a valid phone number!`
    }
  }
});

module.exports = mongoose.model('User',userSchema,"users");