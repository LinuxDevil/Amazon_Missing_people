const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    let missingPerson = await MissingPerson.findById(event.id).exec();
    if(missingPerson) {
        missingPerson.set(event.edits);
        missingPerson.save();
        return {
        "status":200,
            body:JSON.stringify({
                "message":"Missing Person Data Updated!"
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
