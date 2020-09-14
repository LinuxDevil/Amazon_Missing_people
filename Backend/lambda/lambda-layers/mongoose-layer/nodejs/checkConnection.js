const mongoose = require('mongoose'); 

module.exports = async(databaseConnectionURI) => {
  if(mongoose.connection.readyState !== 1) { 
    await mongoose.connect(databaseConnectionURI,{
      bufferCommands: false,
      bufferMaxEntries: 0,
      useNewUrlParser:true,
      useUnifiedTopology:true 
    })
  }
}