const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let FoundPerson = require('/opt/nodejs/models/FoundPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    let foundPerson = await FoundPerson.findById(event.id).exec();
    if(foundPerson) {
        foundPerson.set(event.edits);
        foundPerson.save();
        return {
        "status":200,
            body:JSON.stringify({
                "message":"Found Person Data Updated!"
            })
        };
    }
    else {
        return {
        "status":200,
            body:JSON.stringify({
                "message":"No Person found with that ID!"
            })
        };
    }
    
    
    
};
