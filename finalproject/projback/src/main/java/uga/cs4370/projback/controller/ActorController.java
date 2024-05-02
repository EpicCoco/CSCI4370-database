package uga.cs4370.projback.controller;

import uga.cs4370.projback.models.*;

import java.util.ArrayList;
import java.util.List;

public class ActorController {
    private List<Actor> actors;

    public ActorController() {
        this.actors = new ArrayList<>();
    }

    // Add a new actor to the list
    public void addActor(Actor actor) {
        actors.add(actor);
    }

    // Get all movies with a certain actor in it
    public List<Movie> getMoviesWithActor(String actorName) {
        List<Movie> moviesWithActor = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getName().equalsIgnoreCase(actorName)) {
                moviesWithActor.addAll(actor.getMovies());
            }
        }
        return moviesWithActor;
    }

    // Get all awards for an actor
    public List<Award> getAwardsForActor(String actorName) {
        List<Award> awardsForActor = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getName().equalsIgnoreCase(actorName)) {
                awardsForActor.addAll(actor.getAwards());
            }
        }
        return awardsForActor;
    }

    // Get actor's info (name, age, etc)
    public Actor getActorInfo(String actorName) {
        for (Actor actor : actors) {
            if (actor.getName().equalsIgnoreCase(actorName)) {
                return actor;
            }
        }
        return null; // Actor not found
    }
}
