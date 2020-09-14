const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);


exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    return "works";
};
