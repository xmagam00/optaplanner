use optaplanner

optaplanner.user.insert({
	_id : ObjectId(),
	user_name : "Martin",
	password : "Martin",
	name_of_organization: "Redhat",
	order: "0",
	


})

optaplanner.roles.insert({
	_id : ObjectId(),
	name_of_role: "reader"


})

optaplanner.roles.insert({
	_id : ObjectId(),
	name_of_role: "administrator"


})

optaplanner.roles.insert({
	_id : ObjectId(),
	name_of_role: "planner"


})