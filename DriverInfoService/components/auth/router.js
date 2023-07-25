const express = require("express")
const router = express.Router();


router.get("/", function(req, res, next)
{
    console.log(req);
    res.json("This is Driver-service auth welcome!")
})

module.exports = router;