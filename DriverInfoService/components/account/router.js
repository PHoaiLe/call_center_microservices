const express = require("express")
const router = express.Router();

router.get("/", function(req, res, next)
{
    console.log(req);
    res.json("This is driver's account access!")
})


module.exports = router;