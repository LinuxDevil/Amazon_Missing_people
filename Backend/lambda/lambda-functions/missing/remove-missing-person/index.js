const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    await MissingPerson.findById(event.id).remove();
    
    return {
        "status":200,
        body:JSON.stringify({
            "message":"Missing Person Record Removed"
        })
    };
};
