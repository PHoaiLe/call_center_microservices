const express = require('express');
  
const app = express();
const PORT = 8086;

const mainRouter = require("./components/router");

app.use(express.json());


app.use("/driver_info", mainRouter);

app.listen(PORT, (error) =>{
    if(!error)
        console.log("Server is Successfully Running," 
                   +"and App is listening on port "+ PORT)
    else 
        console.log("Error occurred, server can't start", error);
    }
);


module.exports = app
