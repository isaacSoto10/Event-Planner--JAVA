package services;



import org.springframework.stereotype.Service;

import repositories.EventRepository;
import repositories.MessageRepository;
import repositories.UserEventRepository;
import repositories.UserRepository;



@Service
public class EventService {
	private final UserRepository userRepo;
	private final EventRepository eventRepo;
	private final UserEventRepository userEventRepo;
	private final MessageRepository messageRepo;
	
	public EventService(UserRepository userRepo, EventRepository eventRepo, UserEventRepository userEventRepo, MessageRepository messageRepo) {
		this.userRepo = userRepo;
		this.eventRepo = eventRepo;
		this.userEventRepo = userEventRepo;
		this.messageRepo = messageRepo;
	}
}
