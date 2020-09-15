const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    
    const result = await MissingPerson.aggregate([
    {
      $search: {
        "text": {
          "query":event.name,
          "path":"name"
        }
      }
    }
  ]);
    if(result.length == 0) return "No Result Found.";
    return result;
};
