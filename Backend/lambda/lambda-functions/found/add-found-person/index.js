const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let FoundPerson = require('/opt/nodejs/models/FoundPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    const newFoundPerson = new FoundPerson(event);
    newFoundPerson.save();
    
    return {
        "status":200,
        body:JSON.stringify({
            "message":"New Found Person Added"
        })
    };
};
