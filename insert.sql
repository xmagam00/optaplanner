insert into role(id_role,name_of_role) values (1,'administrator');
insert into role(id_role,name_of_role) values (2,'reader');
insert into role(id_role,name_of_role) values (3,'planner');

insert into state_task(id_state,state) values (1,'paused');
insert into state_task(id_state,state) values (2,'stopped');
insert into state_task(id_state,state) values (3,'running');

insert into user(id_user,user_name,password,name_of_organization,user_role,task) values(1,'martin','martin','Red Hat',1,1);
insert into user(id_user,user_name,password,name_of_organization,user_role,task) values(2,'david','david','IBM',2,2);
insert into user(id_user,user_name,password,name_of_organization,user_role,task) values(3,'peter','peter','IBM',3,3);

insert into task(id_task,xml_file,state_of_task,progress_of_task,url) values (1,"<?xml version="1.0" encoding="UTF-8"?>
<solver>
  <!--<environmentMode>FAST_ASSERT</environmentMode>-->
  <!-- Domain model configuration -->
  <solutionClass>org.optaplanner.examples.cloudbalancing.domain.CloudBalance</solutionClass>
  <planningEntityClass>org.optaplanner.examples.cloudbalancing.domain.CloudProcess</planningEntityClass>
  <!-- Score configuration -->
  <scoreDirectorFactory>
    <scoreDefinitionType>HARD_SOFT</scoreDefinitionType>
    <simpleScoreCalculatorClass>org.optaplanner.examples.cloudbalancing.solver.score.CloudBalancingSimpleScoreCalculator</simpleScoreCalculatorClass>
    <!--<scoreDrl>/org/optaplanner/examples/cloudbalancing/solver/cloudBalancingScoreRules.drl</scoreDrl>-->
  </scoreDirectorFactory>
  <!-- Optimization algorithms configuration -->
  <termination>
    <maximumSecondsSpend>120</maximumSecondsSpend>
  </termination>
  <constructionHeuristic>
    <constructionHeuristicType>FIRST_FIT_DECREASING</constructionHeuristicType>
    <!--forager-->
      <pickEarlyType>FIRST_NON_DETERIORATING_SCORE</pickEarlyType>
    <!--/forager-->
  </constructionHeuristic>
  <localSearch>
    <acceptor>
      <entityTabuSize>7</entityTabuSize>
    </acceptor>
    <forager>
      <acceptedCountLimit>1000</acceptedCountLimit>
    </forager>
  </localSearch>
</solver>",2,0,"http://www.google.sk");

insert into task(id_task,xml_file,state_of_task,progress_of_task,url) values (2,"<?xml version="1.0" encoding="UTF-8"?>
<solver>
  <!--<environmentMode>FAST_ASSERT</environmentMode>-->
  <!-- Domain model configuration -->
  <solutionClass>org.optaplanner.examples.cloudbalancing.domain.CloudBalance</solutionClass>
  <planningEntityClass>org.optaplanner.examples.cloudbalancing.domain.CloudProcess</planningEntityClass>
  <!-- Score configuration -->
  <scoreDirectorFactory>
    <scoreDefinitionType>HARD_SOFT</scoreDefinitionType>
    <simpleScoreCalculatorClass>org.optaplanner.examples.cloudbalancing.solver.score.CloudBalancingSimpleScoreCalculator</simpleScoreCalculatorClass>
    <!--<scoreDrl>/org/optaplanner/examples/cloudbalancing/solver/cloudBalancingScoreRules.drl</scoreDrl>-->
  </scoreDirectorFactory>
  <!-- Optimization algorithms configuration -->
  <termination>
    <maximumSecondsSpend>120</maximumSecondsSpend>
  </termination>
  <constructionHeuristic>
    <constructionHeuristicType>FIRST_FIT_DECREASING</constructionHeuristicType>
    <!--forager-->
      <pickEarlyType>FIRST_NON_DETERIORATING_SCORE</pickEarlyType>
    <!--/forager-->
  </constructionHeuristic>
  <localSearch>
    <acceptor>
      <entityTabuSize>7</entityTabuSize>
    </acceptor>
    <forager>
      <acceptedCountLimit>1000</acceptedCountLimit>
    </forager>
  </localSearch>
</solver>",2,0,"http://www.google3.sk");


insert into task(id_task,xml_file,state_of_task,progress_of_task,url) values (3,"<?xml version="1.0" encoding="UTF-8"?>
<solver>
  <!--<environmentMode>FAST_ASSERT</environmentMode>-->
  <!-- Domain model configuration -->
  <solutionClass>org.optaplanner.examples.cloudbalancing.domain.CloudBalance</solutionClass>
  <planningEntityClass>org.optaplanner.examples.cloudbalancing.domain.CloudProcess</planningEntityClass>
  <!-- Score configuration -->
  <scoreDirectorFactory>
    <scoreDefinitionType>HARD_SOFT</scoreDefinitionType>
    <simpleScoreCalculatorClass>org.optaplanner.examples.cloudbalancing.solver.score.CloudBalancingSimpleScoreCalculator</simpleScoreCalculatorClass>
    <!--<scoreDrl>/org/optaplanner/examples/cloudbalancing/solver/cloudBalancingScoreRules.drl</scoreDrl>-->
  </scoreDirectorFactory>
  <!-- Optimization algorithms configuration -->
  <termination>
    <maximumSecondsSpend>120</maximumSecondsSpend>
  </termination>
  <constructionHeuristic>
    <constructionHeuristicType>FIRST_FIT_DECREASING</constructionHeuristicType>
    <!--forager-->
      <pickEarlyType>FIRST_NON_DETERIORATING_SCORE</pickEarlyType>
    <!--/forager-->
  </constructionHeuristic>
  <localSearch>
    <acceptor>
      <entityTabuSize>7</entityTabuSize>
    </acceptor>
    <forager>
      <acceptedCountLimit>1000</acceptedCountLimit>
    </forager>
  </localSearch>
</solver>",2,0,"http://www.google2.sk");