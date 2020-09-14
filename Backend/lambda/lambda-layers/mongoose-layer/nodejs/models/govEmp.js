const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const governmentEmployeeSchema = new Schema ({
  name : {

  },
  department : {

  }
});

module.exports = mongoose.model('governmentEmployee',governmentEmployeeSchema,"governmentEmployees");