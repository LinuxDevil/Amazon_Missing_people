const mongoose = require('mongoose'); 

module.exports = async() => {
  if(mongoose.connection.readyState !== 1) { 
    await mongoose.connect('mongodb+srv://evan:evan@mern.xupmz.mongodb.net/teckathon?retryWrites=true&w=majority',{
      bufferCommands: false,
      bufferMaxEntries: 0,
      useNewUrlParser:true,
      useUnifiedTopology:true 
    })
  }
}