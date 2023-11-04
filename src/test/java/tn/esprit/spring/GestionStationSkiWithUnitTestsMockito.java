package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controllers.SubscriptionRestController;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GestionStationSkiWithUnitTestsMockito {
    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    @Test
    public void testAddSubscription() {
        // Create a sample subscription
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2023, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setPrice(100.0f);

        // Mock the repository to return the saved subscription
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Test adding a subscription
        Subscription savedSubscription = subscriptionService.addSubscription(subscription);

        assertEquals(subscription, savedSubscription);
    }
    @Test
    public void testRetrieveSubscriptionById() {
        // Create a sample subscription
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.of(2023, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setPrice(100.0f);

        // Mock the repository to return the subscription when searching by ID
        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));

        // Test retrieving a subscription by ID
        Subscription retrievedSubscription = subscriptionService.retrieveSubscriptionById(1L);

        assertEquals(subscription, retrievedSubscription);
    }

    @Test
    public void testGetSubscriptionsByType() {
        // Create sample subscriptions of the specified type
        Subscription subscription1 = new Subscription();
        subscription1.setStartDate(LocalDate.of(2023, 1, 1));
        subscription1.setTypeSub(TypeSubscription.ANNUAL);
        subscription1.setPrice(100.0f);

        Subscription subscription2 = new Subscription();
        subscription2.setStartDate(LocalDate.of(2023, 2, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);
        subscription2.setPrice(120.0f);

        Set<Subscription> annualSubscriptions = Set.of(subscription1, subscription2);

        // Mock the repository to return the subscriptions of the specified type
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(TypeSubscription.ANNUAL)).thenReturn(annualSubscriptions);

        // Test getting subscriptions by type
        Set<Subscription> retrievedSubscriptions = subscriptionService.getSubscriptionByType(TypeSubscription.ANNUAL);

        assertEquals(annualSubscriptions, retrievedSubscriptions);
    }

    @Test
    public void testUpdateSubscription() {
        // Create a sample subscription
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.of(2023, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setPrice(100.0f);

        // Mock the repository to return the updated subscription
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Test updating a subscription
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);

        assertEquals(subscription, updatedSubscription);
    }

    @Test
    public void testGetSubscriptionsByDates() {
        // Create sample subscriptions with start dates within the specified range
        Subscription subscription1 = new Subscription();
        subscription1.setStartDate(LocalDate.of(2023, 1, 1));
        subscription1.setTypeSub(TypeSubscription.ANNUAL);
        subscription1.setPrice(100.0f);

        Subscription subscription2 = new Subscription();
        subscription2.setStartDate(LocalDate.of(2023, 2, 1));
        subscription2.setTypeSub(TypeSubscription.MONTHLY);
        subscription2.setPrice(20.0f);

        List<Subscription> subscriptionsWithinRange = Arrays.asList(subscription1, subscription2);

        // Mock the repository to return subscriptions within the specified date range
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 2, 28);
        when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(subscriptionsWithinRange);

        // Test getting subscriptions by date range
        List<Subscription> retrievedSubscriptions = subscriptionService.retrieveSubscriptionsByDates(startDate, endDate);

        assertEquals(subscriptionsWithinRange, retrievedSubscriptions);
    }
}
