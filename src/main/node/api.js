const cookieName = 'sessionid';

var users = [
  {
    username: "sid",
    password: "god",
    
    
  }
];
var instruments = [
  {
    instrument: "Flute",
    imageURL: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSclQcWMM-1vHvKOQUM-RLbJe9clq8eXFppOA&usqp=CAU"
  }
];
var clarinetMouthpieces = ["Yamaha 4C", "Yamaha 5c", "Vandoren B50", "Vandoren RL40" , "Selmer Focus"];
var saxophoneMouthpieces = ["Stock" , "Yamaha Custom EX" , "Mark IV prestige", "Jody Jazz Titanium", "Selmer C Star" ];
    

export function getUsers(req, res) {
  let username = getUserFromCookie(req);
  if (username == null) return res.send(401);
  else return res.json(users);
};

function getUserFromCookie(req) {
  let username = req.cookies[cookieName];
  for (const cur_user of users) {
    if ((cur_user.username == username)) {
      return cur_user;
    }
  }
  return null;
}

export function getUser(req, res) {
  let user = getUserFromCookie(req);
  if (user != null)
    return res.json(user);
  else
    return res.sendStatus(401);
};


export function getInstrument(req,res) {
  return res.json(instruments);
}

export function getEquipment(req,res) {
    var userJson = getUserFromCookie(req);
    return res.json(userJson.equipJson); 
}

export function getMouthpieceInfo(req,res) {
  let user = getUserFromCookie(req);
  if (user == null) return res.sendStatus(401);
  let instrument = req.query.instrument;
  if (instrument == "Clarinet") {
    return res.json(clarinetMouthpieces);
  }
  else if (instrument == "Saxophone") {
    return res.json(saxophoneMouthpieces);
  }
  else {
    return res.json([]);
  }
}

export function getInstrumentInfo(req,res) {
    let instrumentInfo = [];
    let instrument = req.query.instrument;
    if (instrument==undefined) return res.sendStatus(400);
    else 
    {
      for (const currentUser of users) {
        console.log(currentUser.equipJson.instrument);
        if ((currentUser.equipJson) && (currentUser.equipJson.instrument) && currentUser.equipJson.instrument == instrument) {
            instrumentInfo.push({
              username: currentUser.username, 
              equipJson: currentUser.equipJson
            });
        }
    }
    return res.json(instrumentInfo);
  }
}

export function createUser(req, res) {
  var user = req.body;
  let status = true;
  users.push(user);
  return res.json({"status": status});
};

export function loginUser(req, res) {
  let status = false;
  for (const cur_user of users) {
    if ((cur_user.username == req.body.username) && (cur_user.password == req.body.password)) {
      res.cookie(cookieName, cur_user.username);
      status = true;
      break;
    }
  };
  return res.json({ "status": status });
};

export function changePassword(req, res) {
  let status = false;
  var userJson = getUserFromCookie(req);
  if (userJson == null) {
    return res.json({"status": status});
  }
  else {
    userJson.password = req.body.password;
    status = true;
    return res.json({"status": status});
  }
}
//takes user input and stores it in the bandUser object
export function processEquipment(req,res) {
  let status = false; 
  var userJson = getUserFromCookie(req);
  if (userJson == null) {
    return res.json({"status": status});
  }
  else {
    userJson.equipJson = req.body;
    status = true;
    return res.json({"status": status});
  }

}

export function repetitiveUser(req,res) {
  let status = false;
  for (const currentUser of users) {
    if (currentUser.username == req.body.username) {
      status = true;
      return res.json({"status": status})
    }
  }
  return res.json({"status": status})
}
