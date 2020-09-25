package ArrayOperations;
import java.util.Scanner; //imported scanner

// Author Donald Calhoun

public class TaxReturn {
	public static void main(String[] args) {
		
		//declared variables
		double annualGross = 0.0;
		double adjIncome = 0.0;
		double exemptions = 0.0;
		double taxOwed = 0.0;
		double taxRate1 = 0.15;
		double taxRate2 = 0.28;
		double taxRate3 = 0.31;
		int numExemptions = 0;
		int dependents = 0;
		
		Scanner scan = new Scanner(System.in); //created scan scanner
		//asked for filing status
		System.out.print("Please enter your filing status as either \"single\" or \"married\": "); 

		String status = scan.next(); //set status to string
		if (!status.equals("single") &&	!status.equals("married")) //made sure valid input was given
		{
			System.out.println("Invalid Input");
			return;
		} else {
			System.out.print("Enter number of dependents: "); // asked for # of dependents
			if (scan.hasNextInt()) //checked to make sure input was valid
			{
				dependents = scan.nextInt(); // set dependents 
				if (dependents >= 0) // checked to makes sure dependents wasn't negative
				{
					if (status.equals("single")) //saw if status equals single
					{
						numExemptions = 1 + dependents; //set number of exemptions
						System.out.print("Enter annual income: "); //asked for income
						if (scan.hasNextDouble()) //checked to make sure input was valid
						{
							double income = scan.nextDouble(); //set income 
							if (income < 0) //made sure income wasn't negative
							{
								System.out.println("Invalid Input");
								return;
							} else {
							annualGross = income;
							}
						} else {
							System.out.println("Invalid Input");
							return;
						}
					} else {
						numExemptions = 2 + dependents; //set exemptions for married
						System.out.print("Enter your income: "); 
						if (scan.hasNextDouble()) //checked to make sure input was valid
						{
							System.out.print("Enter spouse's income: "); // asked for spouses income
							double income = scan.nextDouble();
							double spouseIncome = scan.nextDouble();
							if (income < 0 || spouseIncome <0) //made sure incomes were positive
							{
								System.out.println("Invalid Input");
								return;
							} else {
							annualGross = income + spouseIncome; //calculated annual gross income
							}
						} else {
						System.out.println("Invalid Input");
						return;
						}
					}
					
					//calculated how much money needed to be exempted
					if (numExemptions < 5)
					{
						exemptions = numExemptions * 5000;
					} else  {
						exemptions = numExemptions * 4500;
					}
					
					adjIncome = annualGross - exemptions; //Calculated adjusted income
	
					//checking tax rate and tax owed
					if (adjIncome <= 0)
					{
						taxOwed = 0.0;
					} else {
						if (status.equals("single"))
						{
							//checking and calculating tax
							if (adjIncome <= 21450.0)
							{
								taxOwed = taxRate1 * adjIncome;
							} else if (adjIncome <= 51900.0)
							{
								taxOwed = taxRate2 * adjIncome;
							} else {
								taxOwed = taxRate3 * adjIncome;
							}
						} else {
							if (adjIncome <= 35800.0)
							{
								taxOwed = taxRate1 * adjIncome;
							} else if (adjIncome <= 86500.0)
							{
								taxOwed = taxRate2 * adjIncome;
							} else {
								taxOwed = taxRate3 * adjIncome;
							}
						}
					}
				} else {
					System.out.println("Invalid Input");
					return;
				}
			} else {
				System.out.println("Invalid Input");
				return;
			}
			//Outputing the results
			System.out.println("Filing Status     Annual Gross Income     Dependents     Tax Owed ");
			System.out.printf("%-10s %,16.2f %15d %,21.2f", status, annualGross, dependents, taxOwed);
			scan.close(); //closing scanner
		}
	}
}
