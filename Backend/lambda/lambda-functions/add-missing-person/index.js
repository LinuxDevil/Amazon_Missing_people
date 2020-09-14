const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    const newMissingPerson = new MissingPerson(event);
    newMissingPerson.save();
    
    return {
        "status":200,
        body:JSON.stringify({
            "message":"New Missing Person Added"
        })
    };
};
