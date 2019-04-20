// package dev.acs.auth.core.persistence;

// import dev.acs.auth.module.user.User;
// import dev.acs.auth.module.user.service.IUserService;
// import dev.acs.auth.module.usergroup.UserGroup;
// import dev.acs.auth.module.usergroup.service.IUserGroupService;

// public class DatabasePopulator {

// 	private IUserService userService;
	
// 	private IUserGroupService userGroupService;
	
// //	@PostConstruct
// 	public void populate(){
// 		populateUsers();
// 	}

// 	private void populateUsers() {

// 		User user1 = new User("Admin");
// 		userService.save(user1);
		
// 		User user2 = new User("Alex");
// 		userService.save(user2);

// 		User user3 = new User("João");
// 		userService.save(user3);

// 		User user4 = new User("Manchinha");
// 		userService.save(user4);

		
// 		UserGroup userGroup1 = new UserGroup("Admin", "System's admins");
// 		userGroup1.addUser(user1);
// 		userGroupService.save(userGroup1);

		
// 	}
// }
